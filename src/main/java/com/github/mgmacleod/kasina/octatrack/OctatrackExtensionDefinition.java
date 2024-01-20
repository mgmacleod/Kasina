package com.github.mgmacleod.kasina.octatrack;

import java.util.UUID;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class OctatrackExtensionDefinition extends ControllerExtensionDefinition {

	private static final UUID DRIVER_ID = UUID.fromString("774066ce-4678-11ee-be56-0242ac120002");

	@Override
	public String getHardwareVendor() {
		return "Missing Green Mammals";
	}

	@Override
	public String getHardwareModel() {
		return "Octatrack";
	}

	@Override
	public int getNumMidiInPorts() {
		return 1;
	}

	@Override
	public int getNumMidiOutPorts() {
		return 1;
	}

	@Override
	public void listAutoDetectionMidiPortNames(AutoDetectionMidiPortNamesList list, PlatformType platformType) {
		return;
	}

	@Override
	public ControllerExtension createInstance(ControllerHost host) {
		return new OctatrackExtension(this, host);
	}

	@Override
	public String getName() {
		return "Octatrack";
	}

	@Override
	public String getAuthor() {
		return "mgmacleod8";
	}

	@Override
	public String getVersion() {
		return "0.1";
	}

	@Override
	public UUID getId() {
		return DRIVER_ID;
	}

	@Override
	public int getRequiredAPIVersion() {
		return 18;
	}

}
