package com.kh.spring.loadmap.model.service;

import java.util.List;

import com.kh.spring.loadmap.model.dto.GitCommit;
import com.kh.spring.loadmap.model.dto.Loadmap;

public interface LoadmapService {

	String insertGit(Loadmap loadmap);

	Loadmap selectLoadmap(String wsIdx);

	List<GitCommit> selectGitCommitListByLmIdx(String lmIdx);

	GitCommit selectNewCommitList(Loadmap loadmap);

}
