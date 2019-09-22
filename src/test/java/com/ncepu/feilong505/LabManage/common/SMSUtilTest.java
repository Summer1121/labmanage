package com.ncepu.feilong505.LabManage.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SMSUtilTest {

	@Test
	public void sendMessage() {
		SMSUtil.sendMessage(new SMSUtil.MessageParam().setMobile("12345678910")
				.setSignId(1111)
				.setTempId(1)
				.setTempPara("\"code\":\"12345\""));
	}
}
