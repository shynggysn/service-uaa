package kz.ne.railways.tezcustoms.service.dto;

import kz.ne.railways.tezcustoms.service.model.preliminary_information.DkrData;
import kz.ne.railways.tezcustoms.service.model.preliminary_information.Expeditor;
import kz.ne.railways.tezcustoms.service.model.preliminary_information.FormData;
import org.springframework.beans.BeanUtils;

public class FormDataDto {

    public static FormData toFormData(DkrData dkrData){
        FormData formData = new FormData();
        BeanUtils.copyProperties(dkrData, formData);


        return formData;
    }

}
