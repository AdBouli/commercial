/* vue client */

/* derniers rendez-vous avec un client */
SELECT * FROM rdvs
INNER JOIN clients ON clientRdv = idClient
INNER JOIN villes ON vllieClient = idVille
INNER JOIN commerciaux ON comRdv = idCom
WHERE clientRdv = 0 AND comRdv = 0
ORDER BY dateRdv DESC heureRdv DESC;

/* derniers appels avec un client */
SELECT * FROM appels
INNER JOIN clients ON clientAppel = idClient
INNER JOIN villes ON vllieClient = idVille
INNER JOIN commerciaux ON comAppel = idCom
WHERE clientAppel = 0 AND comAppel = 0
ORDER BY dateAppel DESC heureAppel DESC;

/* vue com */

/* les meilleurs / moins bons rendez-vous de la semaine / du mois (client / prospect) */
SELECT * FROM (rdvs/appels)
INNER JOIN clients ON (clientRdv/clientAppel) = idClient
INNER JOIN commerciaux ON (comRdv/comAppels) = idCom
WHERE (comRdv/comAppel) = 0 AND typeClient = (1/0)
AND (avisRdv/avisAppel) (>/<) (SELECT avg(avisRdv/avisAppel) FROM (rdvs/appels) WHERE (comRdv/comAppel) = 0 AND typeClient = (0/1) AND (dateRdv/dateAppel) > '-1sem/mois');

