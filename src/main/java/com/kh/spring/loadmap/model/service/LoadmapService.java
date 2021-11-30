package com.kh.spring.loadmap.model.service;

import com.kh.spring.loadmap.model.dto.Loadmap;

public interface LoadmapService {

	String insertGit(Loadmap loadmap);

	Loadmap selectLoadmap(String wsIdx);

}
