package net.darkaqua.blacksmith.mod.modloader;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.event.EventBus;
import net.darkaqua.blacksmith.api.event.IEvent;
import net.darkaqua.blacksmith.mod.event.BS_EventBus;
import net.darkaqua.blacksmith.mod.event.modloader.InitEvent;
import net.darkaqua.blacksmith.mod.event.modloader.PostInitEvent;
import net.darkaqua.blacksmith.mod.event.modloader.PreInitEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModLoaderManager {

	private static final List<BlacksmithModContainer> loadedMods = Lists.newArrayList();
	private static BlacksmithModContainer activeMod;

	private ModLoaderManager() {}

	public static void registerPlugin(BlacksmithModContainer container, Object instance) {
		loadedMods.add(container);
		activeMod = container;
		EventBus.registerEventListener(instance);
		activeMod = null;
	}

	public static String findName(Object plugin) {
		if (plugin == null)
			return null;
		for (BlacksmithModContainer o : loadedMods) {
			if (plugin.equals(o.modInstance)) {
				return o.getModId();
			}
		}
		return null;
	}

	public static List<BlacksmithModContainer> getLoadedMods(){
		return new ArrayList<>(loadedMods);
	}

	public static BlacksmithModContainer getActiveMod(){
		return activeMod;
	}

	public static void firePreInit(FMLPreInitializationEvent event) {
		postEvent(new PreInitEvent(event));
	}

	public static void fireInit(FMLInitializationEvent event) {
		postEvent(new InitEvent(event));
	}

	public static void firePostInit(FMLPostInitializationEvent event) {
		postEvent(new PostInitEvent(event));
	}

	private static void postEvent(IEvent e){
		HashMap<Class<?>, ArrayList<BS_EventBus.SubscribedMethod>> listeners = BS_EventBus.getEventListeners();
		ArrayList<BS_EventBus.SubscribedMethod> methods = null;
		for (Class<?> clazz : listeners.keySet()) {
			if (clazz.isInstance(e)) {
				methods = listeners.get(clazz);
				break;
			}
		}
		if (methods != null) {
			for (BS_EventBus.SubscribedMethod m : methods) {
				activeMod = m.getModOwner();
				m.call(e);
			}
		}
		activeMod = null;
	}
}