package com.myf.wchat.domain.repository;

import com.myf.wchat.domain.messageDomain.request.Etlocationmessagereq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtlocationmessagereqRepository extends JpaRepository<Etlocationmessagereq,String> {

	Etlocationmessagereq findByEtmsgid(Long msgid);
}
