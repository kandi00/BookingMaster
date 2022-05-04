using ApiHeroku.Data.Response;
using ApiHeroku.Exceptions;
using ApiHeroku.Repositories;
using ApiHeroku.Utils;

namespace ApiHeroku.Services
{
    public class AccommodationService : IAccommodationService
    {
        private readonly IAccommodationRepository _accomodationRepository;

        public AccommodationService(IAccommodationRepository accomodationRepository)
        {
            _accomodationRepository = accomodationRepository;
        }

        public async Task<AccommodationsResponse> GetAccomodations()
        {
            try
            {
                AccommodationsResponse response = new AccommodationsResponse();
                response.Accomodations = await _accomodationRepository.GetAccomodations(); ;

                if (response.Accomodations != null)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.GET_ACCOMMODATION_SUCCESS;
                }
                return response;
            }
            catch (GetRequestException ex)
            {
                throw new GetException(ex.Message);
            }
        }

        public async Task<AccommodationsResponse> GetAccomodationsByLocation(string Location, DateOnly FromDate, DateOnly ToDate)
        {
            try
            {
                AccommodationsResponse response = new AccommodationsResponse();
                response.Accomodations = await _accomodationRepository.GetAccomodationsByLocation(Location, FromDate, ToDate); ;

                if (response.Accomodations != null)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.GET_ACCOMMODATION_SUCCESS;
                }
                return response;
            }
            catch (GetRequestException ex)
            {
                throw new GetException(ex.Message);
            }
        }

        public async Task<AccommodationsResponse> GetAccomodationsByName(string? Name, DateOnly FromDate, DateOnly ToDate)
        {
            try
            {
                AccommodationsResponse response = new AccommodationsResponse();
                response.Accomodations = await _accomodationRepository.GetAccomodationsByName(Name, FromDate, ToDate);

                if (response.Accomodations != null)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.GET_ACCOMMODATION_SUCCESS;
                }
                return response;
            }
            catch (GetRequestException ex)
            {
                throw new GetException(ex.Message);
            }
        }
}
}
