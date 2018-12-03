package com.myf.wchat.domain.repository;

import com.myf.wchat.domain.messageDomain.request.Etlinkmessagereq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtlinkmessagereqRepository extends JpaRepository<Etlinkmessagereq,String> {
	Etlinkmessagereq findByEtmsgid(Long msgid);
}
