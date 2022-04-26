package kz.ne.railways.tezcustoms.service.controller;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/servlet")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ServletController {
    private static final long serialVersionUID = 1L;
    private static final String METHOD = "method";

    private final ForDataBeanLocal dataBean;

    private Gson gson = new Gson();

    @GetMapping
    @Operation(summary = "", description = "")
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam(name = METHOD, required = true) String method) throws IOException {

    }

    @PostMapping
    @Operation(summary = "", description = "")
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam(name = METHOD, required = true) String method) throws ServletException, IOException {
        String json = null;

        if ("getContracts".equals(method)) {
            json = dataBean.getContracts(request.getUserPrincipal().getName()); // TODO: Validate search parameter (username or id)
        } else if ("loadContract".equals(method)) {
            json = loadContract(request);
        } else if ("getContractData".equals(method)) {
            json = getContractData(request);
        }

        if (json != null)
            response.getWriter().write(json);
    }

    private String loadContract(HttpServletRequest request) {
        return null;
    }

    private String getContractData(HttpServletRequest request) {
        return null;
    }
}
