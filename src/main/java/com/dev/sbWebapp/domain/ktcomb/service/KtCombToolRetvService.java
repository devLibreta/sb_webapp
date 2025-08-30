package com.dev.sbWebapp.domain.ktcomb.service;

import com.dev.sbWebapp.domain.ktcomb.dto.KtCombToolListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KtCombToolRetvService {


    public KtCombToolListDto ktCombToolListRetv(){
        KtCombToolListDto.KtCombToolDto tool1 = KtCombToolListDto.KtCombToolDto.builder()
                .id("combsearch")
                .name("결합정보조회")
                .description("결합계약ID로 결합테이블 조회")
                .build();

        KtCombToolListDto.KtCombToolDto tool2 = KtCombToolListDto.KtCombToolDto.builder()
                .id("delSvcContLock")
                .name("서비스계약 락 제거")
                .description("서비스계약 락 제거")
                .build();

        KtCombToolListDto.KtCombToolDto tool3 = KtCombToolListDto.KtCombToolDto.builder()
                .id("devSvcLogDownload")
                .name("DEV 환경 서비스 로그 다운로드")
                .description("서비스로그 경로로 서비스 로그 다운로드")
                .build();

        KtCombToolListDto.KtCombToolDto tool4 = KtCombToolListDto.KtCombToolDto.builder()
                .id("sitSvcLogDownload")
                .name("SIT 환경 서비스 로그 다운로드")
                .description("서비스로그 경로로 서비스 로그 다운로드")
                .build();


        return KtCombToolListDto.builder()
                .ktCombToolList(List.of(tool1, tool2, tool3, tool4))
                .build();
    }
}
