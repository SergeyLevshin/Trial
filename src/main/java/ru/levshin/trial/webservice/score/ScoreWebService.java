package ru.levshin.trial.webservice.score;

import ru.levshin.trial.model.Score;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://service.trial/", name = "ScoreWebService")
public interface ScoreWebService {

    @WebResult(name = "addScore", targetNamespace = "")
    @RequestWrapper(
            localName = "addScore",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.score.ScoreWebService")
    @WebMethod(action = "urn:AddScore")
    @ResponseWrapper(
            localName = "addScoreResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.score.ScoreWebServiceImpl")
    Score addScore(@WebParam(name = "score") Score score);
}
