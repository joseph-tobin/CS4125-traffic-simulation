<h2>Development Notes</h2>
Document detailing what patterns are being used and where they will be implemented.

 <h4>Producer - Consumer Pattern</h4>
 * Used to separate units of work and the execution of that work.
    * Passing movements from Vehicles to be animated on the front.
    * Decouples backend from animations
    * Avoids business logic waiting on animations to complete before continuing execution. 
    
  * Consumables placed into a Queue that is started in a new thread from the Simulation. Refs to queue thread passed to relevant producers/ consumers.
    * Reduces processing load on main Simulation thread.
    * Increases number of passed refs within the system.