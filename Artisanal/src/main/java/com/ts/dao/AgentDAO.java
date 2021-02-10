package com.ts.dao;

import org.hibernate.SessionFactory;

import com.ts.db.HibernateTemplate;
import com.ts.dto.Agent;
import com.ts.dto.Customer;

public class AgentDAO {
	
	private SessionFactory factory = null;

	public static Agent getAgentByUserPass(String loginId, String password) {
		
		return (Agent)HibernateTemplate.getAgentByUserPass(loginId,password);
	}
	public int register(Agent agent) {		
		return HibernateTemplate.addObject(agent);
	}

}
