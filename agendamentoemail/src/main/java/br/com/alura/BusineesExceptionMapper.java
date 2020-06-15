package br.com.alura;

import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.alura.dto.MensagemErroDto;
import br.com.alura.exception.BusinessException;

@Provider
public class BusineesExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(BusinessException exception) {

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity( MensagemErroDto.build(exception.getMensagens()))
                .build();
    }
}
