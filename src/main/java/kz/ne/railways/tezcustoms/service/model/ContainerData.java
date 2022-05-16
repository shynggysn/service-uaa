package kz.ne.railways.tezcustoms.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerData {
    private Long containerListUn;
    private String NumContainer;
    private Integer containerFilled;
    private Long vagonAccessory;// принадлежность контейнера
    private String containerMark;
    private String containerCode;// Типоразмер контейнера

    private String containerCodeName;
    private String vagonAccessoryName;
}
