package com.greenlog.greenlog.service;

import com.greenlog.greenlog.entity.Tree;
import com.greenlog.greenlog.repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreeService {

    private final TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public List<Tree> getAllTrees() {
        return treeRepository.findAll();
    }

    public Optional<Tree> getTreeById(Long id) {
        return treeRepository.findById(id);
    }

    public Tree saveTree(Tree tree) {
        return treeRepository.save(tree);
    }

    public void deleteTree(Long id) {
        treeRepository.deleteById(id);
    }
}