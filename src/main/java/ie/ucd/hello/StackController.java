package ie.ucd.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StackController {
    private Map<String, LinkedStack<String>> stack = new HashMap<String, LinkedStack<String>>();
    private ArrayList<StackDescription> descriptions = new ArrayList<StackDescription>();

    @RequestMapping(value="/stack", method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createQueue(@RequestBody StackDescription description) {
        descriptions.add(description);
        stack.put(description.getName(), new LinkedStack<String>());
    }

    @RequestMapping(value="/stack", method=RequestMethod.GET)
    public ArrayList<StackDescription> listQueues() {
        return descriptions;
    }

    @RequestMapping(value="/stack/{name}/size", method=RequestMethod.GET)
    public StackSize getSize(@PathVariable String name) {
        LinkedStack<String> queue = stack.get(name);
        if (queue == null) throw new NoSuchStackException();
        return new StackSize(queue.size());
    }

    @RequestMapping(value="/stack/{name}", method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enqueue(@PathVariable String name, @RequestBody StackItem item) {
        LinkedStack<String> queue = stack.get(name);
        if (queue == null) throw new NoSuchStackException();
        queue.push(item.getItem());
    }

    @RequestMapping(value="/stack/{name}", method=RequestMethod.GET)
    public StackItem head(@PathVariable String name) {
        LinkedStack<String> queue = stack.get(name);
        if (queue == null) throw new NoSuchStackException();
        return new StackItem(queue.head());
    }

    @RequestMapping(value="/stack/{name}", method=RequestMethod.DELETE)
    public StackItem dequeue(@PathVariable String name) {
        LinkedStack<String> queue = stack.get(name);
        if (queue == null) throw new NoSuchStackException();
        return new StackItem(queue.pop());
    }
}