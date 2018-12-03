package com.myf.wchat.domain.repository;

import com.myf.wchat.domain.messageDomain.request.Etvideomessagereq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtvideomessagereqRepository extends JpaRepository<Etvideomessagereq,String> {
	Etvideomessagereq findByEtmsgid(Long msgid);
}
