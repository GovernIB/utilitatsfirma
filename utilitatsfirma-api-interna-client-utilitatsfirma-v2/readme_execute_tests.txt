(1) Crees una classe que estengui d'aquesta: per exemple MyUtilitatsFirmaV2ApiTest extends UtilitatsFirmaV2ApiTest.
(2) Afegeixes un main()
(3) Fas que aquest main cridi a firma en servidor: 
            MyUtilitatsFirmaV2ApiTest test = new MyUtilitatsFirmaV2ApiTest();
            test.testSignatureServerPAdES();
(4) Copies utilitatsfirma.properties.sample a utilitatsfirma.properties i ajustes basePath, username i password
(5) Arranques el programa MyUtilitatsFirmaV2ApiTest