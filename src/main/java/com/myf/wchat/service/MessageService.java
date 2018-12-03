package com.myf.wchat.service;

import com.myf.wchat.domain.messageDomain.request.*;
import com.myf.wchat.domain.messageDomain.response.Article;
import com.myf.wchat.domain.messageDomain.response.Etrespmessage;
import com.myf.wchat.domain.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MeiYF
 * @time 2018/11/27 17:18
 **/
@Service("messageService")
public class MessageService {

    @Autowired
    private EttextmessagereqRepository ettextmessagereqRepository;
    @Autowired
    private EtrespmessageRepository etrespmessageRepository;
    @Autowired
    private EtimagemessagereqRepository etimagemessagereqRepository;
    @Autowired
    private EtvoicemessagereqRepository etvoicemessagereqRepository;
    @Autowired
    private EtvideomessagereqRepository etvideomessagereqRepository;
    @Autowired
    private EtlocationmessagereqRepository etlocationmessagereqRepository;
    @Autowired
    private EtlinkmessagereqRepository etlinkmessagereqRepository;
    @Autowired
    private ArticleRepository articleRepository;


    /**
     * 查询这条发送的文本信息是否已经存在
     * @param msgid
     * @return
     */
    public synchronized Ettextmessagereq checkEttextmessagereqMsgId(Long msgid){
        return ettextmessagereqRepository.findByEtmsgid(msgid);
    }

    /**
     * 将收到的文本信息存库
     * @param ettextmessagereq
     */
    public synchronized void saveEttextmessagereq(Ettextmessagereq ettextmessagereq){
        ettextmessagereqRepository.save(ettextmessagereq);
    }

    /**
     * 查询这条发送的图片信息是否已经存在
     * @param msgid
     * @return
     */
    public synchronized Etimagemessagereq checkEtimagemessagereqMsgId(Long msgid){
        return etimagemessagereqRepository.findByEtmsgid(msgid);
    }

    /**
     * 将收到的图片信息存库
     * @param etimagemessagereq
     */
    public synchronized void saveEtimagemessagereq(Etimagemessagereq etimagemessagereq){
        etimagemessagereqRepository.save(etimagemessagereq);
    }


    /**
     * 查询这条发送的语音信息是否已经存在
     * @param msgid
     * @return
     */
    public synchronized Etvoicemessagereq checkEtvoicemessagereqMsgId(Long msgid){
        return etvoicemessagereqRepository.findByEtmsgid(msgid);
    }

    /**
     * 将收到的语音信息存库
     * @param etvoicemessagereq
     */
    public synchronized void saveEtvoicemessagereq(Etvoicemessagereq etvoicemessagereq){
        etvoicemessagereqRepository.save(etvoicemessagereq);
    }

    /**
     * 查询这条发送的视频信息是否已经存在
     * @param msgid
     * @return
     */
    public synchronized Etvideomessagereq checkEtvideomessagereqMsgId(Long msgid){
        return etvideomessagereqRepository.findByEtmsgid(msgid);
    }

    /**
     * 将收到的视频信息存库
     * @param etvideomessagereq
     */
    public synchronized void saveEtvideomessagereq(Etvideomessagereq etvideomessagereq){
        etvideomessagereqRepository.save(etvideomessagereq);
    }

    /**
     * 查询这条发送的地理位置信息是否已经存在
     * @param msgid
     * @return
     */
    public synchronized Etlocationmessagereq checkEtlocationmessagereqMsgId(Long msgid){
        return etlocationmessagereqRepository.findByEtmsgid(msgid);
    }

    /**
     * 将收到的地理位置信息存库
     * @param etlocationmessagereq
     */
    public synchronized void saveEtlocationmessagereq(Etlocationmessagereq etlocationmessagereq){
        etlocationmessagereqRepository.save(etlocationmessagereq);
    }

    /**
     * 查询这条发送的链接信息是否已经存在
     * @param msgid
     * @return
     */
    public synchronized Etlinkmessagereq checkEtlinkmessagereqMsgId(Long msgid){
        return etlinkmessagereqRepository.findByEtmsgid(msgid);
    }

    /**
     * 将收到的链接位置信息存库
     * @param etlinkmessagereq
     */
    public synchronized void saveEtlinkmessagereq(Etlinkmessagereq etlinkmessagereq){
        etlinkmessagereqRepository.save(etlinkmessagereq);
    }


    /**
     * 保存回复的Article
     * @param Article
     */
    public synchronized void saveArticles(List<Article> Article){
        articleRepository.saveAll(Article);
    }

    /**
     * 将回复的消息存库
     * @param etrespmessage
     */
    public synchronized void saveEtrespmessage(Etrespmessage etrespmessage){
        etrespmessageRepository.save(etrespmessage);
    }


}
