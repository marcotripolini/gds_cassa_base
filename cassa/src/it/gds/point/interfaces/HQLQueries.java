package it.gds.point.interfaces;

public interface HQLQueries {

	public static final String HQL_LOGIN = "FROM Cliente WHERE (tessera.tessera = :tessera OR tessera.codice_alternativo = :tessera) AND attivo = '1' order by id";
	public static final String HQL_LOGIN_ID = "FROM Cliente WHERE (id= :id) AND attivo = '1'";
	//public static final String HQL_CLIDETT = "FROM ClienteInCorso WHERE id IN (:idc) AND tessera.tessera <> 'NOCARD' ORDER BY id";
	public static final String HQL_CLIDETT = "FROM ClienteInCorso WHERE id IN (:idc) ORDER BY id";

	public static final String HQL_BARORDER = "FROM OrdineNegozio WHERE id_cliente = :id_cliente AND payed = '0' AND type = 'B'";
	public static final String HQL_BARORDER_ID = "FROM OrdineNegozio WHERE id = :id_ordine AND payed = '0' AND type = 'B'";
	public static final String HQL_BARORDER_ID_PRINT = "FROM OrdineNegozioPrint WHERE id = :id_ordine AND payed = '0' AND type = 'B'";
	public static final String HQL_BARORDER_ID_LIST = "FROM OrdineNegozio WHERE id in (__ORDERS__) AND payed = '0' AND type = 'B'";
	public static final String HQL_AFFORDER = "FROM OrdineNegozio WHERE id_cliente = :id_cliente AND payed = '0' AND type = 'A'";
	public static final String HQL_PANEORDER = "FROM OrdineNegozio WHERE id_cliente = :id_cliente AND payed = '0' AND type = 'P'";
	public static final String HQL_TOTEMORDER = "FROM OrdineTotem WHERE id_cliente = :id_cliente AND executed = '1' AND payed = '0' AND type = 'T'";

	public static final String HQL_PRODBYEAN = "FROM Prodotto WHERE ean = :ean";
	public static final String HQL_PRODBYCOD = "FROM Prodotto WHERE codice_gds = :codice_gds";

	public static final String HQL_TOTEMORDERBYID = "FROM OrdineTotem WHERE id = :id_ordine";
	public static final String HQL_ORDEDETT = "FROM OrdineDettagli WHERE id = :id_ordine";

}
