/*
 * Copyright (C) 2004-2017, GoodData(R) Corporation. All rights reserved.
 * This source code is licensed under the BSD-style license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */
package com.gooddata.export;

import com.gooddata.AbstractGoodDataAT;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExportServiceAT extends AbstractGoodDataAT {

    private ExportService service;

    @BeforeMethod
    public void setUp() throws Exception {
        service = gd.getExportService();
    }

    @Test(groups = "export", dependsOnGroups = "report")
    public void exportReportDefinition() throws Exception {
        service.export(reportDefinition, ExportFormat.CSV, System.out);
    }

    @Test(groups = "export", dependsOnGroups = "report")
    public void shouldExportDashboard() throws Exception {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        service.exportPdf(dashboard, dashboard.getTabs().iterator().next(), output).get();
        assertThat(output, is(notNullValue()));
    }

    @Test(groups = "export", dependsOnGroups = "report")
    public void shouldReportDefinitionRaw() throws Exception {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        service.exportCsv(reportDefinition, output).get();
        assertThat(output, is(notNullValue()));
    }
}