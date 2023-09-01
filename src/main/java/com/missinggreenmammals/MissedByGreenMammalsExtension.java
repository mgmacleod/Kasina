package com.missinggreenmammals;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Transport;
import com.bitwig.extension.controller.ControllerExtension;

public class MissedByGreenMammalsExtension extends ControllerExtension
{
   protected MissedByGreenMammalsExtension(final MissedByGreenMammalsExtensionDefinition definition, final ControllerHost host)
   {
      super(definition, host);
   }

   @Override
   public void init()
   {
      final ControllerHost host = getHost();      

      // TODO: Perform your driver initialization here.
      // For now just show a popup notification for verification that it is running.
      host.showPopupNotification("MissedByGreenMammals Initialized");
   }

   @Override
   public void exit()
   {
      // TODO: Perform any cleanup once the driver exits
      // For now just show a popup notification for verification that it is no longer running.
      getHost().showPopupNotification("MissedByGreenMammals Exited");
   }

   @Override
   public void flush()
   {
      // TODO Send any updates you need here.
   }


}
