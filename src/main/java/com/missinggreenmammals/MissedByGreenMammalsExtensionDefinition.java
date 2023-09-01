package com.missinggreenmammals;
import java.util.UUID;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class MissedByGreenMammalsExtensionDefinition extends ControllerExtensionDefinition
{
   private static final UUID DRIVER_ID = UUID.fromString("b2b510d4-a06c-4422-83d2-6ecd65b18d1a");
   
   public MissedByGreenMammalsExtensionDefinition()
   {
   }

   @Override
   public String getName()
   {
      return "MissedByGreenMammals";
   }
   
   @Override
   public String getAuthor()
   {
      return "mgmacleod8";
   }

   @Override
   public String getVersion()
   {
      return "0.1";
   }

   @Override
   public UUID getId()
   {
      return DRIVER_ID;
   }
   
   @Override
   public String getHardwareVendor()
   {
      return "Missing Green Mammals";
   }
   
   @Override
   public String getHardwareModel()
   {
      return "MissedByGreenMammals";
   }

   @Override
   public int getRequiredAPIVersion()
   {
      return 18;
   }

   @Override
   public int getNumMidiInPorts()
   {
      return 1;
   }

   @Override
   public int getNumMidiOutPorts()
   {
      return 1;
   }

   @Override
   public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
   {
   }

   @Override
   public MissedByGreenMammalsExtension createInstance(final ControllerHost host)
   {
      return new MissedByGreenMammalsExtension(this, host);
   }
}
