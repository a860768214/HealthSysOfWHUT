package com.whut.health_sys.service;

import com.whut.health_sys.controller.viewobject.ClinicVO;
import com.whut.health_sys.controller.viewobject.PsyDocVO;
import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.dataobject.PsyDocDO;

import java.util.List;

public interface PsyDocService
{
    PsyDocDO searchPsyDoc(String name);

    List<PsyDocDO> listPsyDoc();

    PsyDocDO selectPsyDoc(Integer did);

    PsyDocDO savePsyDoc(PsyDocDO psyDocDO);

    PsyDocDO updatePsyDoc(PsyDocDO psyDocDO);

    PsyDocDO ClosePsyDoc(PsyDocDO psyDocDO);
}
