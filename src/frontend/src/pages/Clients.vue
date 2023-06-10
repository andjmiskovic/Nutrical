<template>
  <NavBar active-page="clients"></NavBar>

  <div
      style="margin: 20px 20px 20px 100px;"
  >
    <div class="p-inputgroup" style="max-width: 500px; margin-bottom: 20px;">
      <span class="p-inputgroup-addon">
        <i class="pi pi-search"></i>
      </span>
      <InputText v-model="search" @input="filteredClients" placeholder="Search clients..."/>
      <Button label="Add Client" style="margin-left: 10px" icon="pi pi-plus" class="p-button-success" @click="openAddClientDialog" />
    </div>

    <DataTable
        class="p-datatable-sm"
        selectionMode="single"
        v-model:selection="selectedClient"
        :value="filteredClients"
        :scrollable="true"
    >
      <Column style="min-width: 30%" field="email" header="Email"></Column>
      <Column style="min-width: 25%" field="name" header="Name"></Column>
      <Column style="min-width: 25%" field="surname" header="Surname"></Column>
      <Column style="min-width: 10%">
        <template #body="rowData">
          <Button label="Details" icon="pi pi-eye" @click="viewDetails(rowData)" class="p-button-info"></Button>
        </template>
      </Column>
      <Column style="min-width: 10%">
        <template #body="rowData">
          <Button label="Plan" icon="pi pi-file" @click="viewPlan(rowData)" class="p-button-success"></Button>
        </template>
      </Column>
    </DataTable>
  </div>
  <AddClientDialog ref="newClientDialog"></AddClientDialog>
</template>

<script>
import {FilterMatchMode} from "primevue/api";
import NavBar from "../components/NavBar.vue";
import AddClientDialog from "@/dialogs/AddClientDialog";

export default {
  name: "Clients",
  components: {
    AddClientDialog,
    NavBar,
  },
  data() {
    return {
      selectedClient: null,
      clients: [],
      search: "",
      filters: {
        email: {value: null, matchMode: FilterMatchMode.STARTS_WITH},
        name: {value: null, matchMode: FilterMatchMode.STARTS_WITH},
        surname: {value: null, matchMode: FilterMatchMode.STARTS_WITH},
      },
    };
  },
  mounted() {
    this.getClients();
  },
  methods: {
    getClients() {
      // Make an API call to fetch the list of clients
      // Replace this with your actual API call implementation
      fetch("/api/clients")
          .then((response) => response.json())
          .then((data) => {
            this.clients = data;
          })
          .catch((error) => {
            console.error("Error fetching clients:", error);
          });
    },
    viewDetails(client) {
      // Handle viewing client details
      console.log("View details for client:", client);
    },
    viewPlan(client) {
      // Handle viewing client plan
      console.log("View plan for client:", client);
    },
    filteredClients() {
      return this.clients.filter((client) => {
        // Apply your search logic based on client properties
        const emailMatch = client.email.toLowerCase().includes(this.search.toLowerCase());
        const nameMatch = client.name.toLowerCase().includes(this.search.toLowerCase());
        const surnameMatch = client.surname.toLowerCase().includes(this.search.toLowerCase());

        // Return true if any of the properties match the search query
        return emailMatch || nameMatch || surnameMatch;
      });
    },
    openAddClientDialog() {
      this.$refs.newClientDialog.visible = true;
      console.log("EOOO")
    },
  },
};
</script>

<style>
</style>
