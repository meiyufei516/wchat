package com.myf.wchat.domain.repository;

import com.myf.wchat.domain.messageDomain.request.Etvoicemessagereq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtvoicemessagereqRepository extends JpaRepository<Etvoicemessagereq,String> {

	Etvoicemessagereq findByEtmsgid(Long msgid);
}
