package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.entity.company.Company;

public interface CSVService {

    void saveLocalTaxData();

    void saveTaxOfficeData();

    void saveAccountTitleData(Company company);
}
