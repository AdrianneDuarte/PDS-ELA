package com.pi.web;

/**
 * Created by Marcelo Júnior on 13/12/2017.
 */

public interface IInterfaceWeb {

    /**
     * MÉTODO ONDE DEVE SER IMPLEMENTADO O COMPORTAMENTO DA ACTIVITY, APÓS FINALIZADA A REQUISIÇÃO WEB
     * SEU FUNCIONAMENTO É ANÁLOGO AO DO MÉTODO onActivityResult, ONDE HÁ:
     *
     * @param codigoDaRequisicao INTEIRO QUE IDENTIFICA A REQUISIÇÃO
     * @param codigoDeResposta   INTEIRO QUE REPRESENTA A RESPOSTA, QUE SÃO: Activity.RESULT_OK, e/ou Activity.RESULT_CANCELED
     * @param objetoDeResposta   OBJETO DE RESPOSTA (CASO SEJA ESPERADO)
     */
    void posTarefaWeb(int codigoDaRequisicao, int codigoDeResposta, Object objetoDeResposta);

    /**
     * DEVE CONTER QUALQUER AÇÃO QUE DEVA SER REALIZADA ANTES DA CHAMADA AO WEBSERVICE,
     * COMO POR EXEMPLO, EXIBIR UM ProgressBar
     */
    void preTarefaWeb();
}
