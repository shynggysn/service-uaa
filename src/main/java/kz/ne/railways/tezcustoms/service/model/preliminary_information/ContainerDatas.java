package kz.ne.railways.tezcustoms.service.model.preliminary_information;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerDatas {
    private List<ContainerData> containerData;
    private List<ContainerData> containerRemData;
}
