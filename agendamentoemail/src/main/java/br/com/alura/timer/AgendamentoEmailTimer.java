package br.com.alura.timer;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entity.AgendamentoEmail;

@Singleton
public class AgendamentoEmailTimer {
	
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	//@Schedule(hour = "6", minute = "0", second = "0")
	@Schedule(hour = "*", minute = "*")
	public void enviarEmailAgendado() {
		
		List<AgendamentoEmail> agendamentoEmails = agendamentoEmailBusiness.listarAgendamentosEmailsNaoEnviados();
		agendamentoEmails
		.stream()
		.forEach(agendamentoEmail ->{
			context.createProducer().send(queue, agendamentoEmail);
			agendamentoEmailBusiness.marcarEnviadas(agendamentoEmail);		
		});
	}

}
