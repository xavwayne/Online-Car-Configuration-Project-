/*
 * Andrew ID: xiaoyuw
 */
package adapter;

import scale.*;
import server.AutoServer;
import exception.*;

/**
 * BuildAuto class to connect the implemented methods in ProxyAutomobile and the
 * prototype methods in the interfaces
 */
public class BuildAuto extends ProxyAutomobile implements CreateAuto,
	UpdateAuto, FixAuto, Scalable, AutoServer {

    // empty body

}
