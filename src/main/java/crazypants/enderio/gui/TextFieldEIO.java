package crazypants.enderio.gui;

import joptsimple.internal.Strings;
import crazypants.gui.IGuiScreen;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class TextFieldEIO extends GuiTextField {

  public interface ICharFilter {

    boolean passesFilter(char c);
  }
  
  public static final ICharFilter FILTER_NUMERIC = new ICharFilter() {
    @Override
    public boolean passesFilter(char c) {
      return Character.isDigit(c);
    }
  };

  public static ICharFilter FILTER_ALPHABETICAL = new ICharFilter() {
    @Override
    public boolean passesFilter(char c) {
      return Character.isAlphabetic(c);
    }
  };

  public static ICharFilter FILTER_ALPHANUMERIC = new ICharFilter() {
    @Override
    public boolean passesFilter(char c) {
      return FILTER_NUMERIC.passesFilter(c) || FILTER_ALPHABETICAL.passesFilter(c);
    }
  };

  private final int xOrigin;
  private final int yOrigin;
  private ICharFilter filter;

  public TextFieldEIO(FontRenderer fnt, int x, int y, int width, int height) {
    super(fnt, x, y, width, height);
    this.xOrigin = x;
    this.yOrigin = y;
  }

  public void init(IGuiScreen gui) {
    this.xPosition = xOrigin + gui.getGuiLeft();
    this.yPosition = yOrigin + gui.getGuiTop();
  }

  public TextFieldEIO setCharFilter(ICharFilter filter) {
    this.filter = filter;
    return this;
  }

  @Override
  public boolean textboxKeyTyped(char c, int p_146201_2_) {
    if(filter == null || filter.passesFilter(c) || c == '\b') {
      return super.textboxKeyTyped(c, p_146201_2_);
    }
    return false;
  }
}