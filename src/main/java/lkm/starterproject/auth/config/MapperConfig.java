package lkm.starterproject.auth.config;

import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.accounting.mapper.document.NormalDocumentMapper;
import lkm.starterproject.accounting.mapper.document.PurchaseAndSalesDocumentMapper;
import lkm.starterproject.accounting.mapper.register.*;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import lkm.starterproject.auth.mapper.MemberMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public MemberMapper memberMapper() {
        return Mappers.getMapper(MemberMapper.class);
    }

    @Bean
    public MemberCompanyMapper memberCompanyMapper() {
        return Mappers.getMapper(MemberCompanyMapper.class);
    }

    @Bean
    public CompanyMapper companyMapper() {
        return Mappers.getMapper(CompanyMapper.class);
    }

    @Bean
    public AddressMapper addressMapper() {
        return Mappers.getMapper(AddressMapper.class);
    }

    @Bean
    public LocalTaxMapper localTaxMapper() {
        return Mappers.getMapper(LocalTaxMapper.class);
    }

    @Bean
    public TaxOfficeMapper taxOfficeMapper() {
        return Mappers.getMapper(TaxOfficeMapper.class);
    }

    @Bean
    public CustomerMapper customerMapper() {
        return Mappers.getMapper(CustomerMapper.class);
    }

    @Bean
    public FinanceMapper financeMapper() {
        return Mappers.getMapper(FinanceMapper.class);
    }

    @Bean
    public CardMapper cardMapper() {
        return Mappers.getMapper(CardMapper.class);
    }

    @Bean
    public AccountTitleMapper accountTitleMapper() {
        return Mappers.getMapper(AccountTitleMapper.class);
    }

    @Bean
    public CompendiumMapper compendiumMapper() {
        return Mappers.getMapper(CompendiumMapper.class);
    }

    @Bean
    public NormalDocumentMapper normalDocumentMapper() {
        return Mappers.getMapper(NormalDocumentMapper.class);
    }

    @Bean
    public PurchaseAndSalesDocumentMapper purchaseAndSalesDocumentMapper() {
        return Mappers.getMapper(PurchaseAndSalesDocumentMapper.class);
    }
}
