package info.loenwind.autosave.handlers.java;

import java.lang.reflect.Type;
import java.util.Set;

import javax.annotation.Nullable;

import info.loenwind.autosave.Registry;
import info.loenwind.autosave.handlers.IHandler;
import info.loenwind.autosave.util.NBTAction;
import net.minecraft.nbt.CompoundNBT;

public class HandleString implements IHandler<String> {

  public HandleString() {
  }

  @Override
  public Class<?> getRootType() {
    return String.class;
  }

  @Override
  public boolean store(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name, String object)
      throws IllegalArgumentException, IllegalAccessException {
    nbt.putString(name, object);
    return true;
  }

  @Override
  public @Nullable String read(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name,
      @Nullable String object) {
    return nbt.contains(name) ? nbt.getString(name) : object;
  }

}
