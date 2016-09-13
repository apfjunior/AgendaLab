package br.com.tcc2.agendalab.model;

public enum EnumClass {

	INICIAL("Em análise"), REJEITADO("Rejeitado"), CONFIRMADO("Confirmado");

	private String desc;

	public String getDesc() {
		return desc;
	}

	EnumClass(String desc) {

		this.desc = desc;
	}

}
