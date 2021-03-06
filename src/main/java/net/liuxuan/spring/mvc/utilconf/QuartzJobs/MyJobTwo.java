package net.liuxuan.spring.mvc.utilconf.QuartzJobs;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Copyright (c) 2010-2016.  by Liuxuan   All rights reserved.
 * ***************************************************************************
 * 源文件名:  net.liuxuan.spring.mvc.utilconf.QuartzJobs.MyJobTwo
 * 功能:
 * 版本:	@version 1.0
 * 编制日期: 2016/3/7 14:38
 * 修改历史: (主要历史变动原因及说明)
 * YYYY-MM-DD |    Author      |	 Change Description
 * 2016/3/7  |    Moses       |     Created
 */
//此标记说明在执行完Job的execution方法后保存JobDataMap当中固定数据,
// 在默认情况下 也就是没有设置 @PersistJobDataAfterExecution的时候 每个job都拥有独立JobDataMap
// 否则改任务在重复执行的时候具有相同的JobDataMap
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyJobTwo extends QuartzJobBean {

    public static final String COUNT = "count";
    private String name;

    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
        int cnt = dataMap.getInt(COUNT);
        JobKey jobKey = ctx.getJobDetail().getKey();
        System.out.println(jobKey + ": " + name + ": " + cnt);
        cnt++;
        dataMap.put(COUNT, cnt);
    }

    public void setName(String name) {
        this.name = name;
    }
}
