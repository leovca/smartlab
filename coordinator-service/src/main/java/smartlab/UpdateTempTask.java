package smartlab;

import org.apache.log4j.Logger;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smartlab.service.TemperatureService;
//import java.util.logging.Logger;

@Component
public class UpdateTempTask {
    @Autowired
    TemperatureService temperatureService;

    @Scheduled(fixedRate = 5000)
    public void update() {
        try {
            temperatureService.updateTemperature();
        }catch (Exception ex){
            Logger.getLogger(UpdateTempTask.class.getName()).error(ex);
            ex.printStackTrace();
        }
    }
}
