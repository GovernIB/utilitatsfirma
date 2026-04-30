![Logo](https://github.com/GovernIB/maven/raw/binaris/utilitatsfirma/UtilitatsFirma_Fons_Blanc.png)

# Servidor d'Utilitats de Firma (UtilitatsFirma, SUF)

Versió Desenvolupament: [Branca 1.0](https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0)

## Descripció

- Servidor d'Utilitats de Firma, és una Aplicació que proporciona els següents serveis web de Firma en Servidor, Upgrade de Firmes i Validació de Firmes

 - La creació d'aquesta aplicació correspon a la primera fase d'intentar alleugerar [PortaFIB](https://github.com/GovernIB/portafib)

- Permet l'ús de múltiples plugins de Firma en Servidor (https://github.com/GovernIB/pluginsib-signatureserver) i plugins de Validació de Firmes (https://github.com/GovernIB/pluginsib-validatesignature)

- Copia el funcionament de gestió de firmes via API emprant Perfils de Firma i Configuracions de firma de PortaFIB


## Integració via API Rest


Servidor d'Utilitats de Firma, és una Aplicació que proporciona els següents serveis:

### 1. Validació de Firmes:
   - Via Web: (https://HOST_UTILITSTS_FIRMA/utilitstsfirmaback/user/validacio/list/1) accedir al Menú d'Usuari i despres pitjar Validacions. Finalment pitjar sober "Crear Validació"
   - Via Rest: API de validació de Firmes clonada de PortaFIB (Només per Integracions ja existents de Validacio de Firmes via API de PortaFIB) <br/>
          (a) Només requereix canvi de URL de https://PORTAFIB_HOST/portafibapi/interna per https://UTILITATSFIRMA_HOST/utilitatsfirmaapi/interna i copiar configuració de PortaFIB a UtilitatsFirma<br/>
          (b) És per integracions ja existents. Per noves integracions es recomanada l'API d'UtilitstsFirma V2<br/>
          (c) API original aquí https://github.com/GovernIB/portafib/tree/portafib-3.0/api-interna-client-signature-v1<br/>
          (d) API clonada aquí https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v1.<br/>
   - Via Rest: Emprant API OpenAPI/SwaggerUI d'Utilitats de Firma V2 (recomanada ja que optimitza l'enviament de fitxers)<br/>
          (a) Codi font java client: https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2<br/>
          (b) SwaggerUI: https://HOST_UTILITSTS_FIRMA/utilitatsfirmaapi/interna/index.html?urls.primaryName=ApiUtilitatsFirmaV2<br/>
          (c) Exemple d'ús: https://github.com/GovernIB/utilitatsfirma/blob/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2/src/test/java/es/caib/utilitatsfirma/api/interna/client/utilitatsfirma/v2/api/UtilitatsFirmaV2ApiTest.java<br/>
          (d) Dependència Maven:<br/>
```
    <dependency>
        <groupId>es.caib.utilitatsfirma</groupId>
        <artifactId>utilitatsfirma-api-interna-client-utilitatsfirma-v2</artifactId>   
        <version>2.0-SNAPSHOT</version>
    <dependency>
```

### 2. Firmes en Servidor i Upgrade de Firmes:
   - Via Web: Encada NO IMPLEMENTAT
   - Via Rest: API de validació de Firmes clonada de PortaFIB (Només per Integracions ja existents de Firma en Servidor via API de PortaFIB) <br/>
          (a) Només requereix canvi de URL de https://PORTAFIB_HOST/portafibapi/interna per https://UTILITATSFIRMA_HOST/utilitatsfirmaapi/interna i copiar configuració de PortaFIB a UtilitatsFirma<br/>
          (b) És per integracions ja existents. Per noves integracions es recomanada l'API d'UtilitatsFirma V2<br/>
          (c) API original aquí https://github.com/GovernIB/portafib/tree/portafib-3.0/api-interna-client-signature-v1<br/>
   - Via Rest:  emprant API OpenAPI/SwaggerUI d'utilitats de Firma V2<br/>
           (a) Codi font java client: https://github.com/GovernIB/utilitatsfirma/tree/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2<br/>
           (b) SwaggerUI: https://HOST_UTILITSTS_FIRMA/utilitatsfirmaapi/interna/index.html?urls.primaryName=ApiUtilitatsFirmaV2<br/>
           (c) Exemple d'ús: https://github.com/GovernIB/utilitatsfirma/blob/utilitatsfirma-1.0/utilitatsfirma-api-interna-client-utilitatsfirma-v2/src/test/java/es/caib/utilitatsfirma/api/interna/client/utilitatsfirma/v2/api/UtilitatsFirmaV2ApiTest.java<br/>
           (d) Dependència Maven:<br/>
```
    <dependency>
        <groupId>es.caib.utilitatsfirma</groupId>
        <artifactId>utilitatsfirma-api-interna-client-utilitatsfirma-v2</artifactId>   
        <version>2.0-SNAPSHOT</version>
    <dependency>
```


