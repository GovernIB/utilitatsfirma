# Servidor d'Utilitats de Firma (utilitatsfirma, SUF)

Servidor d'Utilitats de Firma, és una Aplicació que proporciona els següents serveis via WS:

1. Validació de Firmes:
   - Via Web
   - Via Rest:  emprant API OpenAPI/SwaggerUI d'utilitats de Firma (https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2)
   - Via Rest: API de validació de Firmes clonada de PortaFIB(Veure API original aquí https://github.com/GovernIB/portafib/tree/portafib-3.0/api-interna-client-signature-v1 i API clonada aquí https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v1).


2. Firmes en Servidor i Upgrade de Firmes:
   - Via Rest:  emprant API OpenAPI/SwaggerUI d'utilitats de Firma (https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2)
   - Via Rest: API de validació de Firmes clonada PortaFIB(Veure https://github.com/GovernIB/portafib/tree/portafib-3.0/api-interna-client-signature-v1 i https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v1).


La creació d'aquesta aplicació correspon a la primera fase d'intentar alleugerar [PortaFIB](https://github.com/GovernIB/portafib)

Permet la definició de multiples plugins de Firma en Servidor ([]https://github.com/GovernIB/pluginsib-signatureserver) i plugins de Validació de Firmes ([]https://github.com/GovernIB/pluginsib-validatesignature)
