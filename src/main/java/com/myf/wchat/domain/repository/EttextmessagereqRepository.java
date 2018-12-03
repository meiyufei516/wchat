package com.myf.wchat.domain.repository;

import com.myf.wchat.domain.messageDomain.request.Ettextmessagereq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EttextmessagereqRepository extends JpaRepository<Ettextmessagereq, String> {

	Ettextmessagereq findByEtmsgid(Long msgid);
}
