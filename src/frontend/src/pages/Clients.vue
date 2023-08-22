<template>
  <NavBar active-page="clients"></NavBar>

  <div style="margin: 20px">
    <div class="p-inputgroup" style="max-width: 500px; margin-bottom: 20px;">
      <span class="p-inputgroup-addon">
        <i class="pi pi-search"></i>
      </span>
      <InputText v-model="search" @input="filterClients" placeholder="Search clients..."/>
      <Button label="Add Client"
              style="margin-left: 10px; background-color: var(--green); border-color: var(--green)"
              icon="pi pi-plus"
              class="p-button-success"
              @click="openAddClientDialog"/>
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
      <Column style="min-width: 100px">
        <template #body="rowData">
          <Button v-tooltip="'View details'"
                  icon="pi pi-align-left"
                  @click="viewDetails(rowData)"
                  class="p-button-icon p-button-info"></Button>
        </template>
      </Column>
      <Column style="min-width: 100px">
        <template #body="rowData">
          <Button v-tooltip="'View plan'"
                  icon="pi pi-file"
                  style="background-color: var(--green); border-color: var(--green)"
                  @click="viewPlan(rowData)"
                  class="p-button-icon p-button-success"></Button>
        </template>
      </Column>
    </DataTable>
  </div>
  <AddClientDialog ref="newClientDialog"></AddClientDialog>
  <ClientDetailsDialog ref="clientDetailsDialog"></ClientDetailsDialog>
</template>

<script>
import {FilterMatchMode} from "primevue/api";
import NavBar from "../components/NavBar.vue";
import AddClientDialog from "@/dialogs/AddClientDialog";
import ClientDetailsDialog from "@/dialogs/ClientDetailsDialog";
import ClientsService from "@/services/ClientsService";
import AuthService from "@/services/AuthService";

export default {
  name: "Clients",
  components: {
    AddClientDialog,
    ClientDetailsDialog,
    NavBar,
  },
  data() {
    return {
      selectedClient: null,
      nutritionistEmail: "",
      clients: [],
      filteredClients: [],
      search: "",
      filters: {
        email: {value: null, matchMode: FilterMatchMode.STARTS_WITH},
        name: {value: null, matchMode: FilterMatchMode.STARTS_WITH},
        surname: {value: null, matchMode: FilterMatchMode.STARTS_WITH},
      },
    };
  },
  mounted() {
    AuthService.getLoggedUserInfo().then((data) => {
      this.nutritionistEmail = data[0]["email"];
      this.getClients();
    })
  },
  methods: {
    getClients() {
      ClientsService.getClients(this.nutritionistEmail)
          .then((data) => {
            this.clients = data[0];
            this.filterClients();
          })
          .catch((error) => {
            console.error("Error fetching clients");
            console.error(error);
          });
    },
    viewDetails(rowData) {
      ClientsService.getClientDetails(this.nutritionistEmail, rowData["data"]["email"])
          .then((clientResponse) => {
            console.log(clientResponse)
            this.$refs.clientDetailsDialog.client = clientResponse[0];
            this.$refs.clientDetailsDialog.visible = true;
          })
    },
    viewPlan(rowData) {
      let planId = rowData["data"]["planId"];
      this.$router.push({path: "/plan/" + planId});
    },
    filterClients() {
      this.filteredClients = this.clients.filter((client) => {
        const emailMatch = client.email.toLowerCase().includes(this.search.toLowerCase());
        const nameMatch = client.name.toLowerCase().includes(this.search.toLowerCase());
        const surnameMatch = client.surname.toLowerCase().includes(this.search.toLowerCase());

        return emailMatch || nameMatch || surnameMatch;
      });
    },
    openAddClientDialog() {
      this.$refs.newClientDialog.visible = true;
    },
  },
};
</script>

<style>
</style>
