package com.jeremydyer.service.runnable;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.service.email.EmailManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Ran daily to email me a report of the amount of runnable that I have left on the NVR mount. Two improvements are
 * planned for this later.
 * 1) More detailed info in the email, daily usage breakdowns, camera usage breakdown, etc
 * 2) Will be replaced by a more sophisticated system that simply handles the runnable management rather than alerting me
 *
 * Created by jeremydyer on 3/22/15.
 */
public class DailyStorageReportRunnable
    implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(DailyStorageReportRunnable.class);
    private NVRConfiguration nvrConfiguration = null;
    private EmailManager em = null;

    public DailyStorageReportRunnable(NVRConfiguration nvrConfiguration) {
        this.nvrConfiguration = nvrConfiguration;
        this.em = new EmailManager(nvrConfiguration);
    }

    @Override
    public void run() {
        logger.debug("DailyStorageReportRunnable.run()");
        File f = new File(nvrConfiguration.getNvrVideoDir());

        //Generates the metrics for yesterday
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, (cal.get(Calendar.DAY_OF_MONTH) - 1));
        Date yesterday = cal.getTime();

        logger.debug("Generating DailyStorageReport for: " + yesterday);

        StringBuilder sb = new StringBuilder();
        sb.append(new Date());
        sb.append(" Total Usable Space: ");
        sb.append(f.getUsableSpace() / 1024 / 1024 / 1024 + " GB for: " + f.getAbsolutePath());
        this.em.sendEmail(sb.toString());
    }
}
