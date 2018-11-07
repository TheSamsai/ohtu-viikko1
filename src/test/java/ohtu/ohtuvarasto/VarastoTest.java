package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class VarastoTest {

    Varasto varasto;
    final double vertailuTarkkuus = 0.0001;
    final double oletusKoko = 10;
    final double vaaraKoko = -10;
    final double oletusSaldo = 5;
    final double vaaraSaldo = -5;
    final double liikaaSaldoa = 15;
    final double sopivaLisays = 8;

    @Before
    public void setUp() {
        varasto = new Varasto(oletusKoko);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringNäyttääOikein() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(oletusKoko, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaEiNegatiivinenTilavuus() {
        Varasto v = new Varasto(vaaraKoko);
        
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaTilavuusOikein() {
        Varasto v = new Varasto(oletusKoko, oletusSaldo);
        
        assertEquals(oletusKoko, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaSaldoOikein() {
        Varasto v = new Varasto(oletusKoko, oletusSaldo);
        
        assertEquals(oletusSaldo, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaEiNegatiivinenTilavuus2() {
        Varasto v = new Varasto(vaaraKoko, oletusSaldo);
        
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaEiNegatiivinenSaldo2() {
        Varasto v = new Varasto(oletusKoko, vaaraSaldo);
        
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaEiLiikaaSaldoa() {
        Varasto v = new Varasto(oletusKoko, liikaaSaldoa);
        
        assertEquals(v.getSaldo(), v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(sopivaLisays);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(sopivaLisays, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(sopivaLisays);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisäysEiMuutaSaldoa() {
        double vanhaSaldo = varasto.getSaldo();
        
        varasto.lisaaVarastoon(-1);
        
        assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonSaldoEiKasvaLiikaa() {
        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);
        
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(sopivaLisays);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(sopivaLisays);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals((oletusKoko - sopivaLisays + 2), varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaLiikaa() {
        double saatu = varasto.otaVarastosta(varasto.getSaldo() + 1);
        
        // pitäisi saada tyhjää
        assertEquals(0.0, saatu, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivista() {
        double saatu = varasto.otaVarastosta(-1);
        
        // pitäisi saada tyhjää
        assertEquals(0.0, saatu, vertailuTarkkuus);
    }
}
