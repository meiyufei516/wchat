package com.myf.wchat.domain.repository;

import com.myf.wchat.domain.messageDomain.request.Etimagemessagereq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtimagemessagereqRepository extends JpaRepository<Etimagemessagereq,String> {

	Etimagemessagereq findByEtmsgid(Long msgid);
}
