![Logo](https://github.com/GovernIB/maven/raw/binaris/utilitatsfirma/UtilitatsFirma_Fons_Blanc.png)

# Servidor d'Utilitats de Firma (UtilitatsFirma, SUF)

Versió Desenvolupament: [Branca 1.0](https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0)

Servidor d'Utilitats de Firma, és una Aplicació que proporciona els següents serveis:

1. Validació de Firmes:
   - Via Web
   - Via Rest:  emprant API OpenAPI/SwaggerUI d'utilitats de Firma (https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2)
   - Via Rest: API de validació de Firmes clonada de PortaFIB (Veure API original aquí https://github.com/GovernIB/portafib/tree/portafib-3.0/api-interna-client-signature-v1 i API clonada aquí https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v1).


2. Firmes en Servidor i Upgrade de Firmes:
   - Via Rest:  emprant API OpenAPI/SwaggerUI d'utilitats de Firma (https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2)
   - Via Rest: API de validació de Firmes clonada PortaFIB (Veure API original aquí https://github.com/GovernIB/portafib/tree/portafib-3.0/api-interna-client-signature-v1 i API clonada aquí https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v1).


La creació d'aquesta aplicació correspon a la primera fase d'intentar alleugerar [PortaFIB](https://github.com/GovernIB/portafib)

Permet l'ús de múltiples plugins de Firma en Servidor (https://github.com/GovernIB/pluginsib-signatureserver) i plugins de Validació de Firmes (https://github.com/GovernIB/pluginsib-validatesignature)
