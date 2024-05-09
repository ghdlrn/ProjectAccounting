package lkm.starterproject.auth.config;

import lkm.starterproject.accounting.mapper.CompanyMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public CompanyMapper companyMapper() {
        return Mappers.getMapper(CompanyMapper.class);
    }
}
