package com.jeremydyer.resource;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;

/**
 * Created by jeremydyer on 3/18/15.
 */
@Path("/stream")
public class VideoStream {

    public VideoStream() {

    }

    @GET
    @Produces("video/mp4")
    public Response streamExample() {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                //Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                System.out.println("Streaming the .mp4 file to the browser now");
                IOUtils.copy(new FileInputStream(new File("/home/jeremy/home/jeremy/dahua/Basement4300S/2015-03-18/001/dav/20/20.40.29-20.40.58[M][0@0][0].mp4")), os);
                //writer.write("test");
                //writer.flush();
                os.flush();
                System.out.println("Done writing the file now");
            }
        };
        return Response.ok(stream).build();
    }
}
