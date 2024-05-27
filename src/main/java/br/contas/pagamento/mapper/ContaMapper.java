package br.contas.pagamento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import br.contas.pagamento.dto.ContaDto;
import br.contas.pagamento.entity.Conta;

@Component
@Mapper(componentModel = "spring")
public interface ContaMapper {
	
	ContaDto toDto(Conta conta);
	
	Conta toEntity(ContaDto contaDto);
	
	@InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Conta partialUpdate(ContaDto contaDto, @MappingTarget Conta conta);
	
}
	