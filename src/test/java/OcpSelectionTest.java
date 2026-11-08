import java.util.Collections;
import metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import presentation.Presentation2;
import static org.junit.Assert.assertEquals;

public class OcpSelectionTest {

  @Test
  public void devProfile_choisitDao2_300() {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.getEnvironment().setActiveProfiles("dev");     // DaoImpl2 (150)
    ctx.register(Presentation2.class);
    ctx.refresh();
    IMetier m = ctx.getBean(IMetier.class);
    assertEquals(300.0, m.calcul(), 1e-6);
    ctx.close();
  }

  @Test
  public void prodProfile_choisitDao_200() {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.getEnvironment().setActiveProfiles("prod");    // DaoImpl (100)
    ctx.register(Presentation2.class);
    ctx.refresh();
    IMetier m = ctx.getBean(IMetier.class);
    assertEquals(200.0, m.calcul(), 1e-6);
    ctx.close();
  }

  @Test
  public void daoTarget_choisitDaoApi_440() {
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      ctx.getEnvironment().getPropertySources().addFirst(
          new MapPropertySource("test", Collections.singletonMap("dao.target", "daoApi")));
      ctx.register(Presentation2.class);
      ctx.refresh();
      IMetier m = ctx.getBean(IMetier.class);
      assertEquals(440.0, m.calcul(), 1e-6);
    }
  }
}
