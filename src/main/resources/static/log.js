document.addEventListener("DOMContentLoaded", function() {

    const API = "";

    // ==============================
    // COMMON API FUNCTIONS
    // ==============================

    async function getData(url) {
        try {
            const response = await fetch(API + url);

            if (!response.ok) {
                throw new Error("Request failed: " + response.status);
            }

            return await response.json();
        } catch (error) {
            console.error("GET Error:", error);
            return [];
        }
    }

    async function sendData(url, method, data) {
        try {
            const response = await fetch(API + url, {
                method: method,
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw new Error("Request failed: " + response.status);
            }

            return await response.json();
        } catch (error) {
            console.error(method + " Error:", error);
            throw error;
        }
    }


    // ==============================
    // VOLUNTEERS
    // ==============================

    async function loadVolunteers() {
        const container = document.getElementById("volunteerTable");

        if (!container) {
            return;
        }

        const volunteers = await getData("/api/volunteers");

        container.innerHTML = "";

        volunteers.forEach(function(volunteer) {

            const row = document.createElement("tr");

            row.innerHTML =
                "<td>" + (volunteer.id || "") + "</td>" +
                "<td>" + (volunteer.name || "") + "</td>" +
                "<td>" + (volunteer.email || "") + "</td>" +
                "<td>" + (volunteer.phone || "") + "</td>";

            container.appendChild(row);
        });
    }


    // ==============================
    // PLANTATION DRIVES
    // ==============================

    async function loadPlantationDrives() {
        const container = document.getElementById("driveTable");

        if (!container) {
            return;
        }

        const drives = await getData("/api/plantation-drives");

        container.innerHTML = "";

        drives.forEach(function(drive) {

            const row = document.createElement("tr");

            row.innerHTML =
                "<td>" + (drive.id || "") + "</td>" +
                "<td>" + (drive.name || "") + "</td>" +
                "<td>" + (drive.location || "") + "</td>" +
                "<td>" + (drive.driveDate || "") + "</td>";

            container.appendChild(row);
        });
    }


    // ==============================
    // TREES
    // ==============================

    async function loadTrees() {
        const container = document.getElementById("treeTable");

        if (!container) {
            return;
        }

        const trees = await getData("/api/trees");

        container.innerHTML = "";

        trees.forEach(function(tree) {

            const row = document.createElement("tr");

            row.innerHTML =
                "<td>" + (tree.id || "") + "</td>" +
                "<td>" + (tree.species || "") + "</td>" +
                "<td>" + (tree.plantedDate || "") + "</td>" +
                "<td>" + (tree.latitude || "") + "</td>" +
                "<td>" + (tree.longitude || "") + "</td>" +
                "<td>" + (tree.status || "") + "</td>";

            container.appendChild(row);
        });
    }


    // ==============================
    // CHECK-INS
    // ==============================

    async function loadCheckIns() {
        const container = document.getElementById("checkinTable");

        if (!container) {
            return;
        }

        const checkIns = await getData("/api/check-ins");

        container.innerHTML = "";

        checkIns.forEach(function(checkIn) {

            const row = document.createElement("tr");

            row.innerHTML =
                "<td>" + (checkIn.id || "") + "</td>" +
                "<td>" + (checkIn.checkInDate || "") + "</td>" +
                "<td>" + (checkIn.alive === true ? "Alive" : (checkIn.alive === false ? "Dead" : "")) + "</td>";

            container.appendChild(row);
        });
    }


    // ==============================
    // DASHBOARD
    // ==============================

    async function loadDashboardStats() {
        const volCount = document.getElementById("volunteerCount");
        const drvCount = document.getElementById("driveCount");
        const trCount = document.getElementById("treeCount");
        const chkCount = document.getElementById("checkinCount");

        if (volCount) {
            const volunteers = await getData("/api/volunteers");
            volCount.innerText = volunteers.length;
        }

        if (drvCount) {
            const drives = await getData("/api/plantation-drives");
            drvCount.innerText = drives.length;
        }

        if (trCount) {
            const trees = await getData("/api/trees");
            trCount.innerText = trees.length;
        }

        if (chkCount) {
            const checkIns = await getData("/api/check-ins");
            chkCount.innerText = checkIns.length;
        }
    }


    // ==============================
    // DROPDOWNS
    // ==============================

    async function loadSelectOptions() {
        const driveSelect = document.getElementById("driveId");
        const volunteerSelects = document.querySelectorAll("#volunteerId");
        const treeSelect = document.getElementById("treeId");

        if (driveSelect) {
            const drives = await getData("/api/plantation-drives");
            driveSelect.innerHTML = drives.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
            if (drives.length === 0) {
                driveSelect.innerHTML = `<option value="">-- No Drives Found. Add one first! --</option>`;
            }
        }
        
        if (treeSelect) {
            const trees = await getData("/api/trees");
            treeSelect.innerHTML = trees.map(t => `<option value="${t.id}">${t.species} (ID: ${t.id})</option>`).join('');
            if (trees.length === 0) {
                treeSelect.innerHTML = `<option value="">-- No Trees Found. Add one first! --</option>`;
            }
        }

        if (volunteerSelects.length > 0) {
            const volunteers = await getData("/api/volunteers");
            const options = volunteers.map(v => `<option value="${v.id}">${v.name}</option>`).join('');
            if (volunteers.length === 0) {
                volunteerSelects.forEach(select => select.innerHTML = `<option value="">-- No Volunteers Found. Add one first! --</option>`);
            } else {
                volunteerSelects.forEach(select => select.innerHTML = options);
            }
        }
    }


    // ==============================
    // INITIAL LOAD
    // ==============================

    loadVolunteers();
    loadPlantationDrives();
    loadTrees();
    loadCheckIns();
    loadDashboardStats();
    loadSelectOptions();

    // ==============================
    // FORM SUBMIT HANDLERS
    // ==============================

    window.addVolunteer = async function(event) {
        event.preventDefault();
        const data = {
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            phone: document.getElementById("phone").value
        };
        try {
            await sendData("/api/volunteers", "POST", data);
            document.getElementById("name").value = "";
            document.getElementById("email").value = "";
            document.getElementById("phone").value = "";
            loadVolunteers();
            loadDashboardStats();
            alert("Volunteer added successfully!");
        } catch (e) {
            console.error("Failed to add volunteer", e);
            alert("Failed to add volunteer. Check console for details.");
        }
    };

    window.addDrive = async function(event) {
        event.preventDefault();
        const data = {
            name: document.getElementById("name").value,
            location: document.getElementById("location").value,
            driveDate: document.getElementById("driveDate").value
        };
        try {
            await sendData("/api/plantation-drives", "POST", data);
            document.getElementById("name").value = "";
            document.getElementById("location").value = "";
            document.getElementById("driveDate").value = "";
            loadPlantationDrives();
            loadDashboardStats();
            alert("Plantation Drive added successfully!");
        } catch (e) {
            console.error("Failed to add drive", e);
            alert("Failed to add drive. Check console for details.");
        }
    };

    window.addTree = async function(event) {
        event.preventDefault();
        const driveId = document.getElementById("driveId").value;
        const volunteerId = document.getElementById("volunteerId").value;
        
        if (!driveId || !volunteerId) {
            alert("Error: You must create a Plantation Drive and a Volunteer first before adding a Tree!");
            return;
        }

        const data = {
            species: document.getElementById("species").value,
            plantedDate: document.getElementById("plantedDate").value,
            latitude: document.getElementById("latitude").value,
            longitude: document.getElementById("longitude").value,
            plantationDrive: { id: driveId },
            volunteer: { id: volunteerId }
        };
        try {
            await sendData("/api/trees", "POST", data);
            document.getElementById("species").value = "";
            document.getElementById("plantedDate").value = "";
            document.getElementById("latitude").value = "";
            document.getElementById("longitude").value = "";
            loadTrees();
            loadDashboardStats();
            alert("Tree added successfully!");
        } catch (e) {
            console.error("Failed to add tree", e);
            alert("Failed to add tree. Please check if coordinates are valid and try again.");
        }
    };

    window.addCheckin = async function(event) {
        event.preventDefault();
        const treeId = document.getElementById("treeId").value;
        const volunteerId = document.getElementById("volunteerId").value;
        
        if (!treeId || !volunteerId) {
            alert("Error: You must create a Tree and a Volunteer first before recording a Check-in!");
            return;
        }

        const data = {
            checkInDate: document.getElementById("checkInDate").value,
            alive: document.getElementById("alive").value === "true",
            tree: { id: treeId },
            volunteer: { id: volunteerId }
        };
        try {
            await sendData("/api/check-ins", "POST", data);
            document.getElementById("checkInDate").value = "";
            loadCheckIns();
            loadDashboardStats();
            alert("Check-in recorded successfully!");
        } catch (e) {
            console.error("Failed to add checkin", e);
            alert("Failed to record check-in. Check console for details.");
        }
    };

});