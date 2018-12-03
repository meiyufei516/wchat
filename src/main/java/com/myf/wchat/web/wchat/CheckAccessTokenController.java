package com.myf.wchat.web.wchat;

import com.myf.wchat.config.CashClass;
import com.myf.wchat.schedule.AutoGetAccessToken;
import com.myf.wchat.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 检查access_token是否为空
 * @author MeiYF
 * @time 2018/11/23 16:29
 **/
@Controller
public class CheckAccessTokenController {

    private static final Logger logger= LoggerFactory.getLogger(CheckAccessTokenController.class);

    public static int  checkAccessToken(){
        if(StringUtils.isEmpty(CashClass.access_token)){
            //如果access_token是空的就要去获取一次
            AutoGetAccessToken autoGetAccessToken=new AutoGetAccessToken();
            try {
                autoGetAccessToken.autoGetAccessToken();
            }catch (Exception e){
                logger.error("【"+ DataUtil.getLogTime()+"】获取access_token时Https访问失败："+e.getMessage());
                return 1;
            }
        }
        return 0;
    }


    /**
     * 手动的获取一次AccessToken
     */
    @RequestMapping("/getAccessToken")
    @ResponseBody
    public String getAccessToken(){
        if(StringUtils.isEmpty(CashClass.access_token)){
            //如果access_token是空的就要去获取一次
            AutoGetAccessToken autoGetAccessToken=new AutoGetAccessToken();
            try {
                autoGetAccessToken.autoGetAccessToken();
            }catch (Exception e){
                logger.error("【"+ DataUtil.getLogTime()+"】获取access_token时Https访问失败："+e.getMessage());
            }
        }
        return "新获取的access_token是："+CashClass.access_token;
    }
}
